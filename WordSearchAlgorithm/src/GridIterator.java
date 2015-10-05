import java.util.Iterator;

public class GridIterator<Type> implements Iterable<Type> {
	public Type[][] grid;
	public int i;
	public int j;
	
	public GridIterator(Type[][] grid) {
		this.grid = grid;
	}
	
	@Override
	public Iterator<Type> iterator() {
		 Iterator<Type> it = new Iterator<Type>() {

	            private int currentIndex = 0;

	            @Override
	            public boolean hasNext() {
	                return currentIndex < currentSize && arrayList[currentIndex] != null;
	            }

	            @Override
	            public Type next() {
	                return arrayList[currentIndex++];
	            }

	            @Override
	            public void remove() {
	                throw new UnsupportedOperationException();
	            }
	        };
	        return it;
	}

}
