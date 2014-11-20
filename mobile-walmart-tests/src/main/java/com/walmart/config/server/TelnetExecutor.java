package com.walmart.config.server;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import org.apache.commons.net.telnet.TelnetClient;
import org.apache.log4j.Logger;


public class TelnetExecutor {
	private static final Logger LOG = Logger.getLogger(TelnetExecutor.class);
    private static final int PORT_MIN = 0;
    private static final int PORT_MAX = 65535;
 
    private final String _server;
    private final int _port;
 
    public TelnetExecutor(final String server, final String portStr) {
        if (server == null || server.trim().length() == 0) {
            LOG.warn("Server name has a length of zero. Status result will fail.");
            _server = null;
        } else {
            _server = server;
        }
        int port = Integer.parseInt(portStr);
        if (port < PORT_MIN || port > PORT_MAX) {
            LOG.warn("Server port is out of bounds. Status result will fail.");
            _port = -1;
        } else {
            _port = port;
        }
    }

 
    /**
     * Determine the result of the port request.
     * <p/>
     * A return value of <code>Success</code> indicates successful connection, <code>Error</code> indicates a 
     * configuration problem, <code>Fail</code> indicates a failed connection, and <code>Unknown</code> indicates an 
     * unexpected problem.
     */

	
    public Status getResult() {
        if (_server == null || _port < 0) {
            return Status.ERROR;
        }
 
        Status status;
        final TelnetClient telnetClient = new TelnetClient();
        try {
            telnetClient.connect(_server, _port);
            telnetClient.disconnect();
            status = Status.SUCCESS;
        } catch (ConnectException ce) {
            LOG.info("Could not connect to server '" + _server + "' port " + _port + ". Check if Appium Server is launched.");
            status = Status.FAIL;
        } catch (UnknownHostException e) {
            LOG.error("Unknown host: " + _server);
            status = Status.ERROR;
        } catch (IOException e) {
            LOG.error("Error connecting to server: " + _server + " - " + e.getMessage(), e);
            status = Status.UNKNOWN;
        }
 
        return status;
    }
}
