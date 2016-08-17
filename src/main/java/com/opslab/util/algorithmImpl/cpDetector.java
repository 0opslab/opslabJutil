package com.opslab.util.algorithmImpl;

import info.monitorenter.cpdetector.io.*;

/**
 * Created by Poseidon on 2015/4/5.
 */
public class cpDetector {
    public static CodepageDetectorProxy codepageDetector = CodepageDetectorProxy.getInstance();

    static {
        codepageDetector.add(new ParsingDetector(false));//ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于指示是否显示探测过程的详细信息，为false不显示。
        codepageDetector.add(JChardetFacade.getInstance());//JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。
        codepageDetector.add(ASCIIDetector.getInstance());//ASCIIDetector用于ASCII编码测定
        codepageDetector.add(UnicodeDetector.getInstance());//UnicodeDetector用于Unicode家族编码的测定
    }
}
