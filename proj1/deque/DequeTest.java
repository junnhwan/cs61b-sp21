package deque;

import static org.junit.Assert.*;
import org.junit.Test;

public class DequeTest {

    // 测试ArrayDeque的基础功能
    @Test
    public void testArrayDequeBasic() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        // 测试isEmpty和size
        assertTrue(ad.isEmpty());
        assertEquals(0, ad.size());

        // 测试addFirst和addLast
        ad.addFirst(1);
        ad.addLast(2);
        ad.addFirst(0);
        assertEquals(3, ad.size());
        assertFalse(ad.isEmpty());

        // 测试get
        assertEquals((Integer) 0, ad.get(0));
        assertEquals((Integer) 1, ad.get(1));
        assertEquals((Integer) 2, ad.get(2));
        assertNull(ad.get(-1));
        assertNull(ad.get(3));

        // 测试removeFirst和removeLast
        assertEquals((Integer) 0, ad.removeFirst());
        assertEquals((Integer) 2, ad.removeLast());
        assertEquals(1, ad.size());
        assertEquals((Integer) 1, ad.get(0));
    }

    // 测试LinkedListDeque的基础功能
    @Test
    public void testLinkedListDequeBasic() {
        LinkedListDeque<String> lld = new LinkedListDeque<>();

        // 测试isEmpty和size
        assertTrue(lld.isEmpty());
        assertEquals(0, lld.size());

        // 测试addFirst和addLast
        lld.addFirst("a");
        lld.addLast("b");
        lld.addFirst("0");
        assertEquals(3, lld.size());
        assertFalse(lld.isEmpty());

        // 测试get和getRecursive
        assertEquals("0", lld.get(0));
        assertEquals("a", lld.get(1));
        assertEquals("b", lld.get(2));
        assertEquals("0", lld.getRecursive(0));
        assertEquals("b", lld.getRecursive(2));
        assertNull(lld.get(3));
        assertNull(lld.getRecursive(-1));

        // 测试removeFirst和removeLast
        assertEquals("0", lld.removeFirst());
        assertEquals("b", lld.removeLast());
        assertEquals(1, lld.size());
        assertEquals("a", lld.get(0));
    }

    // 测试空队列的移除操作
    @Test
    public void testRemoveFromEmpty() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();

        assertNull(ad.removeFirst());
        assertNull(ad.removeLast());
        assertNull(lld.removeFirst());
        assertNull(lld.removeLast());
    }

    // 测试ArrayDeque的扩容和缩容
    @Test
    public void testArrayDequeResize() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        // 测试扩容（初始容量8，添加9个元素触发扩容）
        for (int i = 0; i < 9; i++) {
            ad.addLast(i);
        }
        assertEquals(9, ad.size());
        // 扩容后容量应为16（8*2），通过get验证元素是否正确
        for (int i = 0; i < 9; i++) {
            assertEquals((Integer) i, ad.get(i));
        }

        // 测试缩容（移除元素至容量的25%以下）
        for (int i = 0; i < 7; i++) {
            ad.removeFirst(); // 剩余2个元素
        }
        // 此时容量应从16缩为8（16/2）
        // 通过添加元素验证缩容后功能正常
        ad.addFirst(100);
        ad.addLast(200);
        assertEquals(4, ad.size());
        assertEquals((Integer) 100, ad.get(0));
        assertEquals((Integer) 200, ad.get(3));
    }

    // 测试两种实现的一致性（相同操作序列应产生相同结果）
    @Test
    public void testConsistency() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        LinkedListDeque<String> lld = new LinkedListDeque<>();

        // 执行相同的操作序列
        ad.addFirst("x");
        lld.addFirst("x");
        ad.addLast("y");
        lld.addLast("y");
        ad.addFirst("z");
        lld.addFirst("z");

        // 验证size和元素一致性
        assertEquals(ad.size(), lld.size());
        for (int i = 0; i < ad.size(); i++) {
            assertEquals(ad.get(i), lld.get(i));
            assertEquals(ad.get(i), lld.getRecursive(i));
        }

        // 移除元素后再次验证
        ad.removeFirst();
        lld.removeFirst();
        ad.removeLast();
        lld.removeLast();
        assertEquals(ad.get(0), lld.get(0));
    }

    // 测试printDeque（通过控制台输出观察，无断言）
    @Test
    public void testPrintDeque() {
        System.out.println("ArrayDeque打印测试:");
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(1);
        ad.addLast(2);
        ad.addFirst(0);
        ad.printDeque(); // 预期输出: 0 1 2

        System.out.println("LinkedListDeque打印测试:");
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(3);
        lld.addLast(4);
        lld.addFirst(2);
        lld.printDeque(); // 预期输出: 2 3 4
    }
}
