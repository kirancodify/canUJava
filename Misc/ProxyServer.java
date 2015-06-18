package oracle.httptst.proxy;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import oracle.httptst.constants.PROXY;

public class ProxyServer
{
    private static int CMD_SIZE = 100;
    private static String ENCODING = "ISO-8859-1";

    private int m_AdminPort;
    private boolean stopRequested;

    private ServerSocket m_ServerSocket;
    private Socket m_Socket;

    private Hashtable listeners;

    //
    // Constructor
    //
    public ProxyServer(int adminPort)
    {
        this.m_AdminPort = adminPort;
        stopRequested = false;
        listeners = new Hashtable();
    }

    //
    // stopAdmin() Method
    //
    public void stopAdmin()
        throws IOException
    {
        Socket l_Socket = new Socket(PROXY.LOCALHOST, m_AdminPort);
        l_Socket.getOutputStream().write(PROXY.STOPSERVERCODE);
        l_Socket.close();
    }


    //
    // startAdmin() Method
    //
    public void startAdmin()
    {
        System.out.println("Starting proxy server at " + m_AdminPort);

        try
        {
            m_ServerSocket = new ServerSocket(m_AdminPort);

            // accept the connection from my client
            byte[] cmd = new byte[CMD_SIZE];
            int cmdlen;

            m_Socket = m_ServerSocket.accept();
            while (!stopRequested)
            {
                ProxyServerRelay relay = new ProxyServerRelay(m_Socket, this);
                Thread t = new Thread(relay);
                t.start();
                m_Socket = m_ServerSocket.accept();
            }
        }
        catch (Exception e)
        {
            System.err.println(e.toString());
            System.exit(1);
        }
    }


    //
    // startProxy() Method
    //
    public void startProxy(int port, String proxyHost, int proxyPort)
        throws IOException
    {
//        System.out.println("Sending message to server to start proxy at " + proxyHost +":"+proxyPort);
        StringBuffer sb = new StringBuffer();
        sb.append(PROXY.STARTCODE);
        sb.append(' ');
        sb.append(port);
        sb.append(' ');
        sb.append(proxyHost);
        sb.append(' ');
        sb.append(proxyPort);

        Socket l_Socket = new Socket(PROXY.LOCALHOST, m_AdminPort);
        l_Socket.getOutputStream().write(sb.toString().getBytes(ENCODING));
        l_Socket.close();
    }


    //
    // stopProxy() Method
    //
    public void stopProxy(int port)
        throws IOException
    {
        StringBuffer sb = new StringBuffer();
        sb.append(PROXY.STOPCODE);
        sb.append(' ');
        sb.append(port);

        Socket l_Socket = new Socket(PROXY.LOCALHOST, m_AdminPort);
        l_Socket.getOutputStream().write(sb.toString().getBytes(ENCODING));
        l_Socket.close();
    }


    //
    // stopAdminServer() Method
    //
    protected void stopAdminServer()
        throws IOException, InterruptedException
    {
        System.out.println("Stopping Proxy Server");

        stopRequested = true;
        // stop all proxys first

        stopAllListeners();
        if (m_Socket != null)
        {
            m_Socket.close();
        }
        if (m_ServerSocket != null)
        {
            m_ServerSocket.close();
        }

        // generate a dummy connection stop ServerSocket.accept()
        Socket st = new Socket(PROXY.LOCALHOST, m_AdminPort);
        st.close();
    }

    //
    // registerListener() Method
    //
    protected void registerListener(int port, ServerSocket ss)
    {
        listeners.put(new Integer(port), ss);
    }

    //
    // deRegisterListener() Method
    //

    protected void deRegisterListener(int port)
    {
        listeners.remove(new Integer(port));
    }

    //
    // stopAllListeners() Method
    //

    protected void stopAllListeners()
        throws IOException
    {
        Integer port;
        ServerSocket ss;

        Enumeration e = listeners.keys();
        while (e.hasMoreElements())
        {
            port = (Integer) e.nextElement();
            ss = (ServerSocket) listeners.get(port);

            // close ServerSocket
            ss.close();
        }
    }

}
