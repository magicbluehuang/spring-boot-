package com.example.demospringboot;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyPaibGenerator {
    
    @Test
    public  void keyPaiTest()throws Exception{
        //生成公钥私钥
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        rsa.initialize(1024);
        KeyPair keyPair = rsa.genKeyPair();
        //类型转换，只是为了确保是对应的加密方式
        RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey aPublic = (RSAPublicKey) keyPair.getPublic();
        String privateKeyStr = new String(Base64.encodeBase64(aPrivate.getEncoded()));
        System.out.println(privateKeyStr);
        String publicKeyStr = new String(Base64.encodeBase64(aPublic.getEncoded()));
        System.out.println(publicKeyStr);

        //加签
        Signature signature = Signature.getInstance("Sha1WithRSA");
        signature.initSign(KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr))));
        signature.update("df".getBytes());
        byte[] sign = signature.sign();
        String signStr = new String(Base64.encodeBase64(sign));
        System.out.println("加签"+ signStr);

        //验签
        Signature signature2 = Signature.getInstance("Sha1WithRSA");
        signature2.initVerify(KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr))));
        signature2.update("df".getBytes());
        System.out.print(signature2.verify(Base64.decodeBase64(signStr)));


    }
}
