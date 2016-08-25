//****************************************************************************
// ---- version information ----
//
// FileFormatException.java  v 1.00 b1
// Written by:               I. van Rienen / E-mail ivr@bart.nl
// URL:                      http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class handling File Format Exceptions
//
// This program and the Java source is in the public domain.
// Permission to use, copy, modify, and distribute this software
// and its documentation for NON-COMMERCIAL purposes and
// without fee is hereby granted.
//
//    Copyright 1996
//
//    Iwan van Rienen
//    Joan Maetsuyckerstr. 145
//    2593 ZG  The Hague
//    The Netherlands
//
// I am not responsible for any bugs in this program and
// possible damage to hard- or software when using this program.
//****************************************************************************
import java.applet.Applet;
import java.awt.*;
import java.lang.InterruptedException;
import java.lang.Integer;
import java.lang.Math;
import java.util.Vector;
import java.io.StreamTokenizer;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;

// ---------------------------------------------------------------------------
// class FileFormatException
// ---------------------------------------------------------------------------
class FileFormatException extends Exception {
    public FileFormatException(String s) {
        super(s);
    }
}