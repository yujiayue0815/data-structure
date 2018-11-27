package unionFind.impl;

import unionFind.UF;

/**
 * 数组并查集实现方式，此时的实现方式在查看两个节点的是否连接的时候的时间复杂度是 O(1),
 * 但是将两个节点连接在一起的时候的时间复杂度是O(n)级别的，此时的只相当于一维的实现方式
 */
public class UnionFind1 implements UF {

    private int[] id;


    public UnionFind1(int size) {
        this.id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    private int find(int q) {
        if (q < 0 || q >= id.length)
            throw new IllegalArgumentException("Illegal is error");
        return id[q];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void union(int p, int q) {
        int pID = find(p);
        int qId = find(q);
        if (pID == qId)
            return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID)
                id[i] = qId;
        }
    }
}
