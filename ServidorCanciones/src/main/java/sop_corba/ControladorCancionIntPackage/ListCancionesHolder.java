package sop_corba.ControladorCancionIntPackage;


/**
* sop_corba/ControladorCancionIntPackage/ListCancionesHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from interface.idl
* viernes 5 de agosto de 2022 10:16:46 AM CDT
*/

public final class ListCancionesHolder implements org.omg.CORBA.portable.Streamable
{
  public sop_corba.ControladorCancionIntPackage.CancionDTO value[] = null;

  public ListCancionesHolder ()
  {
  }

  public ListCancionesHolder (sop_corba.ControladorCancionIntPackage.CancionDTO[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sop_corba.ControladorCancionIntPackage.ListCancionesHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sop_corba.ControladorCancionIntPackage.ListCancionesHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sop_corba.ControladorCancionIntPackage.ListCancionesHelper.type ();
  }

}
