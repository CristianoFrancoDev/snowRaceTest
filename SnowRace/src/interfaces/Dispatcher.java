package interfaces;

import util.Request;

public interface Dispatcher
{
    void callView(String view, Request request);
}
