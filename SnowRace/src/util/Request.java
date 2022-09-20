package util;

import java.util.HashMap;
import java.util.Map;

public class Request
{
    private Map<String, Object> parametri;

    public Request()
    {
        parametri = new HashMap<>();
    }

    public Object get(String key)
    {
        return parametri.get(key);
    }

    public void put(String key, Object value)
    {
        parametri.put(key, value);
    }

    public String getString(String key)
    {
        Object value = parametri.get(key);

        if (value == null)
            return null;
        else
            return value.toString();
    }
}
