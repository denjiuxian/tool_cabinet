package com.loaves.tool_cabinet;

import com.loaves.tool_cabinet.pattern.HuffmanTree;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ToolCabinetApplicationTests {


    @Test
    void contextLoads() {
        int[] weights = {2, 3, 7, 9, 18, 25};
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.createHuffman(weights);
        huffmanTree.output(huffmanTree.getRoot());
    }

}

