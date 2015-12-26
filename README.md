# Algorithms
A collections of algorithms.

Word Search Solver Algorithm:
It took me a while to conceptualize how to go about solving a word search in efficient time. As most people would do, I first started with an algorithm that visits each point in the grid and checks all 8 directions from that point and checks every single direction against every single word to see if that word is there. As you can probably tell from that explination, it was depressingly slow. It's time was in a realm I would never want to visit. So I broke it down.

Before I jump into this let me clarify some things:
This is the solver for a square wordsearch grid of size nxn and amount of words m. Also, no duplicate words are allowed in the wordlist and two words are not allowed to be nested (i.e. map and maple). Overlapping words ARE allowed (i.e. words that cross at some point in the grid). This is assumed to be in the English language.

Now the break down:
The biggest step in the break down is realizing that you can break apart the grid into directions. You can break this grid into the 8 compass directions and turn those into Strings or char arrays separated by spaces at each line break. Here's an example:
Original:	a b c	
		d e f
		g h i

East:	{a,b,c,' ',d,e,f,' ',g,h,i}
SE:	{c,' ',b,f,' ',a,e,i,' ',d,h,' ',g}
South:	{a,d,g,' ',b,e,h,' ',c,f,i}
etc.

Each of these directions can be traversed in O(n^2) making it a total of approximately 8n^2 which is still O(n^2).
These traversals can find all words in each direction using a Trie. A trie is basically just a word tree.

I'll set aside the traversals for a moment while we construct the Trie.
So the trie becomes a lot easier when using an objective programming language, it would probably be a bit more difficult to implement without objects.
Because there are only 26 possible characters in the English language, our trie will be limited (which is good).

To start the trie we simply start with an empty object array of size 26.
Now we iterate over the list of words, and inevitably iterating over each character (keep this in mind for later).
The first letter of the first word we come to gets indexed. A => 0, B => 1, C => 2, etc.
This index is where we will store another object array inside of our top level object array.
We then go into this new object array we have stored in our top level one.
In here we go to the second letter of the word, index it, and store at its index (in this second level object array) another object array.
We keep doing this until we reach the end of the word. 
When we reach the last letter of the word, instead of storing another object array at its index, we store a boolean (initialized to false).
It is false for now because it will become true later on as we traverse the grid and find this word.
Now for the second word, if the first letter is the same as a previous word's first letter, we don't overwrite the array that's in that index, we simply go into it and follow it until we come across a letter that differs from a previous word, then at that index, store a new array and continue with the process.
We will eventually have all the words stored in this trie with a boolean at the leaf level of each word.
The end result is that we will have some null spaces in our object arrays, simply because we cannot possibly fill every single one.

Okay now for the running time of this process. I told you to keep in mind the fact that we iterate over every character in the wordlist.
This is what limits the running time of making the trie, the amount of characters in the wordlist. No how do we know what this is limited by?
And how do we know that this might not be O(m * n^2) or something greater than O(n^2)? 
Let's bring this down to a small case and see how many possible words we could ever have.
Gird:	a b c
	d e f
	g h i

Now just for the sake of this example, let's pretend that any combination of letters is a valid word. We'll go through and figure out the possible words we could have in any direction.
East:	abc, def, ghi
SE:	c, bf, aei, dh, g
South:	adg, beh, cfi
SW:	a, bd, ceg, fh, i
West:	cba, fed, ihg
NW:	g, hd, iea, fb, c
North:	gda, heb, ifc
NE:	a, db, gec, hf, i

So for this particular grid, these are every single word possible to make in this grid. Now let's look into the amount of characters they total up to.
They all have a total of 9 characters, making a total of 8 * 9 characters or 8 * n^2 characters.
Now you can use proof by induction to prove that the total number of characters poossible is 8 * n^2 for any size grid.
Which means that the time to make the trie can be done in O(n^2) time.

So far, everything has been computed in O(n^2) time.
We know the trie can de done in O(n^2) and we know the traversals can be done in O(n^2), now let's combine them and make sure it still runs in O(n^2) time.

Now for actually finding the words, we will go through the trie while traversing the grid in the 8 different directions.
You start off by always holding a reference (Object) to the top level trie and having another variable to hold the reference of the current level trie you're in.
In the very beginning, you set the current trie to the top level. When you reach the first letter in your traversal, you index it (as I mentioned before) and get the reference held at that index of the current trie. If the reference (Object) is null at that index, you know that letter is not the start of a word and you continue on and do the same process for the next letter.
If that reference was not null, it means you found the first letter of a word in the trie. 
If the object of that reference was a boolean and is false, you know you have reached the end of a word that has not been found yet, so you set the boolean to true (saying that the word has been found, and if that boolean is reached again we will know not to record it more than once). After setting the boolean to true, you record the word (mentioned below) and reset the current trie to the top level trie and continue.
If the object of that reference was another Object array, it means the word continues, so you set the current trie to this new object array, go to the next letter in the grid and repeat this process. 
If you come to a null reference in your current trie, you reset the current trie to the top level trie, retry that letter (because it might be the beginning of another word) and continue.
As you're traversing, if you reach the end of a line and have not found a word, you reset the current trie to the top level trie and continue on to the first letter of the next line.
When you find the end of a word, after setting the boolean to true, you can report that word, its direction, and its ending location to a helper method that will be able to get the starting position of the word (based on its ending location and length) in constant time. This can then store it's location and direction, or print it out, or do whatever you want it to do, because it has found the word.
