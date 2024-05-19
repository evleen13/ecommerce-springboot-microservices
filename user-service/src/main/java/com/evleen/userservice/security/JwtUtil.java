//package com.evleen.userservice.security;
//
//import java.io.UnsupportedEncodingException;
//import java.security.Key;
//import java.util.Date;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.InvalidKeyException;
//
//public class JwtUtil {
//	private static final String SECRET = "sap!entev!een123!@#";
//	private static final long EXPIRATION_TIME = 864_000_000; // 10 days
//
//	public static String generateToken(String username) throws InvalidKeyException, UnsupportedEncodingException {
//		SignatureAlgorithm signatureAlgo = SignatureAlgorithm.ES256;
//
////		SignatureAlgorithm signatureAlgo;
////		Key key = new Key;
////		Key signatureAlgo = signatureAlgo;
//		return Jwts.builder().subject(username).expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//				.signWith(signatureAlgo, SECRET.getBytes("utf-8")).compact();
////			signatureAlgo, SECRET.getBytes("utf-8")
//	}
//
//	public static String extractUsername(String token) {
//		return token;
////		return Jwts.parser().setSigningKey(SECRET).
////				parseClaimsJws(token).getBody().getSubject();
//	}
//
//}
