package com.wondersgroup.falcon.Util.file;


import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterImpl
    implements FilenameFilter
{

    private String sExt;

    public FilenameFilterImpl(String _extendName)
    {
        sExt = _extendName;
    }

    public boolean accept(File _dir, String _name)
    {
        return _name.endsWith(sExt);
    }
}
