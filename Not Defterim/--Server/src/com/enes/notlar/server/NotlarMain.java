package com.enes.notlar.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

public class NotlarMain {

    public static void main( String[] args ) throws Exception
    {
    	NotlarHandler notlarHandler = new NotlarHandler();
    	
        Server server = new Server(8080);
        ContextHandler context = new ContextHandler("/");
        context.setContextPath("/");
        context.setHandler(notlarHandler);

        ContextHandler contextTest = new ContextHandler("/test");
        contextTest.setHandler(notlarHandler);

        ContextHandler contextIT = new ContextHandler("/it");
        contextIT.setHandler(notlarHandler);       

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { context, contextTest, contextIT });

        server.setHandler(contexts);

        server.start();
        server.dumpStdErr();
        server.join();
        
    }
    


    
}
