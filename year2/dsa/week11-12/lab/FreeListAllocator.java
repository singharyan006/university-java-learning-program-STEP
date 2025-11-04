import java.util.*;

public class FreeListAllocator {
    private static class Block {
        int start, length;
        Block(int s, int l) { start = s; length = l; }
    }

    private final LinkedList<Block> freeList;

    public FreeListAllocator(int heapSize) {
        freeList = new LinkedList<>();
        freeList.add(new Block(0, heapSize)); // Initially entire heap is free
    }

    public int malloc(int size) {
        for (ListIterator<Block> it = freeList.listIterator(); it.hasNext();) {
            Block block = it.next();
            if (block.length >= size) {
                int allocStart = block.start;
                if (block.length == size) {
                    it.remove();
                } else {
                    block.start += size;
                    block.length -= size;
                }
                return allocStart;
            }
        }
        return -1; // No fitting block found
    }

    public void free(int ptr, int size) {
        ListIterator<Block> it = freeList.listIterator();
        while (it.hasNext()) {
            if (ptr < it.next().start) {
                it.previous();
                break;
            }
        }
        Block newBlock = new Block(ptr, size);
        it.add(newBlock);

        // Coalesce with previous if adjacent
        int prevIndex = it.previousIndex() - 1;
        if (prevIndex >= 0) {
            Block prev = freeList.get(prevIndex);
            if (prev.start + prev.length == newBlock.start) {
                prev.length += newBlock.length;
                freeList.remove(newBlock); // remove by reference, not iterator remove
                newBlock = prev;
            }
        }

        // Coalesce with next if adjacent
        if (it.hasNext()) {
            Block next = it.next();
            if (newBlock.start + newBlock.length == next.start) {
                newBlock.length += next.length;
                freeList.remove(next); // remove by reference, avoid iterator remove
            }
        }
    }

    public List<int[]> freeList() {
        List<int[]> res = new ArrayList<>();
        for (Block b : freeList) {
            res.add(new int[]{b.start, b.length});
        }
        return res;
    }

    public String freeBlocksView() {
        StringBuilder sb = new StringBuilder();
        for (Block b : freeList) sb.append("[").append(b.start).append(",").append(b.length).append("] ");
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        FreeListAllocator allocator = new FreeListAllocator(20);
        System.out.println("free: " + allocator.freeBlocksView());
        int a = allocator.malloc(8);
        System.out.println("malloc(8) → " + a + " // free: " + allocator.freeBlocksView());
        int b = allocator.malloc(4);
        System.out.println("malloc(4) → " + b + " // free: " + allocator.freeBlocksView());
        allocator.free(a, 8);
        System.out.println("free(0,8) // free: " + allocator.freeBlocksView());
        int c = allocator.malloc(6);
        System.out.println("malloc(6) → " + c + " // free: " + allocator.freeBlocksView());
        allocator.free(b, 4);
        System.out.println("free(8,4) // free: " + allocator.freeBlocksView());
        allocator.free(12, 8);
        System.out.println("free(12,8) // free: " + allocator.freeBlocksView());
        System.out.println("Final free: " + allocator.freeBlocksView());
    }
}
