//****************************************************************************
// ---- version information ----
//
// Example.java          v 1.00 b1
// Written by:           I. van Rienen / E-mail ivr@bart.nl
// URL:                  http://www/bart.nl/~ivr
// Initial release:
// Released in public domain:
//
// ---- Description ----
// Java class containing data and constructor for an example
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
import java.awt.*;

class Example {
    protected String Type;
    protected String Description;
    protected String Location;

//----------------------------------------------------------------------------
// The constructor of a new example
//----------------------------------------------------------------------------
    public Example (String t, String d, String l) {
        Type = t;
        Description = d;
        Location = l;
    }

//----------------------------------------------------------------------------
// Get the example type
//----------------------------------------------------------------------------
    public String getType() {
        return Type;
    }

//----------------------------------------------------------------------------
// Get the example description
//----------------------------------------------------------------------------
    public String getDescription() {
        return Description;
    }

//----------------------------------------------------------------------------
// Get the example location
//----------------------------------------------------------------------------
    public String getLocation() {
        return Location;
    }
}