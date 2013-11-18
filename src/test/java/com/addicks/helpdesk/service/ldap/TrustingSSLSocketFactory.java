package com.addicks.helpdesk.service.ldap;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.stereotype.Service;

@Service
public class TrustingSSLSocketFactory extends SocketFactory {
  // Create a trust manager that does not validate certificate chains
  private TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
    @Override
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
      return null;
    }

    @Override
    public void checkClientTrusted(final X509Certificate[] certs, final String authType) {
    }

    @Override
    public void checkServerTrusted(final X509Certificate[] certs, final String authType) {
    }
  } };

  private SocketFactory trustingFactory;

  public TrustingSSLSocketFactory() {
    // Install the all-trusting trust manager
    SSLContext sc = null;

    try {
      sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
    catch (NoSuchAlgorithmException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    catch (KeyManagementException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // Create all-trusting host name verifier
    HostnameVerifier allHostsValid = new HostnameVerifier() {
      @Override
      public boolean verify(final String hostname, final SSLSession session) {
        return true;
      }
    };

    // Install the all-trusting host verifier
    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    trustingFactory = sc.getSocketFactory();

  }

  @Override
  public Socket createSocket(final String host, final int port) throws IOException,
      UnknownHostException {
    return trustingFactory.createSocket(host, port);
  }

  @Override
  public Socket createSocket(final InetAddress host, final int port) throws IOException {
    return trustingFactory.createSocket(host, port);
  }

  @Override
  public Socket createSocket(final String host, final int port, final InetAddress localHost,
      final int localPort) throws IOException, UnknownHostException {
    return trustingFactory.createSocket(host, port, localHost, localPort);
  }

  @Override
  public Socket createSocket(final InetAddress address, final int port,
      final InetAddress localAddress, final int localPort) throws IOException {
    return trustingFactory.createSocket(address, port, localAddress, localPort);
  }
}
