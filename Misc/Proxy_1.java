package oracle.httptst.proxy;

import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import oracle.httptst.constants.PROXY;

public class Proxy implements Runnable
{
    private int port;
    private int proxyPort;
    private String proxyHost;
    private ProxyServer parent;

    //
    // Constructor
    //
    public Proxy(int port, String proxyHost, int proxyPort, ProxyServer parent)
    {
        this.port = port;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.parent = parent;
    }

    //
    // run() Method
    //
    public void run()
    {
        try
        {
            String prefix = String.valueOf(port);

            ServerSocket l_ServerSocket = new ServerSocket(port);
            parent.registerListener(port, l_ServerSocket);

            // counter for response log file generation
            int count = 0;
            FileOutputStream freq = new FileOutputStream(prefix + PROXY.REQ, false);
            FileOutputStream frsp = null;

            System.out.println("Starting proxy at "+ port +" "+ proxyHost +":"+ proxyPort);

            while (true)
            {
                // new response log file
                count ++;
                frsp = new FileOutputStream(prefix + "_" + String.valueOf(count) + PROXY.RES, false);

                // accept the connection from my client
                Socket l_ReqSocket = l_ServerSocket.accept();
                // connect to redirected proxy
                Socket l_ResSocket = new Socket(proxyHost, proxyPort);

                // relay the stuff thru
                Relay req = new Relay(l_ReqSocket.getInputStream(), l_ResSocket.getOutputStream(), freq);
                Relay rsp = new Relay(l_ResSocket.getInputStream(), l_ReqSocket.getOutputStream(), frsp);

                Thread reqThread = new Thread(req);
                Thread rspThread = new Thread(rsp);

                reqThread.start();
                rspThread.start();
            }
        }
        catch (Exception e)
        {
            // do nothing
        }
    }

}
