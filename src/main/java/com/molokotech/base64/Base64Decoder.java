package com.molokotech.base64;

/**
 * Abstraction over Base 64 decoding implementations that work across Java versions.
 */
public abstract class Base64Decoder {

  private static final Base64Decoder INSTANCE;
  static {
    Base64Decoder instance;
    try {
      Class.forName("java.util.Base64");
      // If succeeds, then:
      instance = new Java8Base64Decoder();
    } catch (ClassNotFoundException cnfe) {
      instance = new JAXBBase64Decoder();
    }
    INSTANCE = instance;
  }

  /**
   * @param s Base-64 encoded string
   * @return bytes that the string encodes
   */
  abstract byte[] decode(String s);

  static Base64Decoder getInstance() {
    return INSTANCE;
  }

}
