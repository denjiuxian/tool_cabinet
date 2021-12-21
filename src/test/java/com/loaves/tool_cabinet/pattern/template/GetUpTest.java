package com.loaves.tool_cabinet.pattern.template;

import com.loaves.tool_cabinet.pattern.HuffmanTree;
import org.junit.jupiter.api.Test;

public class GetUpTest {

    @Test
    void test01() {
        //早起上班
        new GetUpEarly().work();

        //正常上班
        new GetUp().work();

        //晚起上班
        new GetUpLate().work();
    }

    @Test
    void test02() {

        int[] weights = {2, 3, 7, 9, 18, 25};
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.createHuffman(weights);
        huffmanTree.output(huffmanTree.getRoot());
    }

}
