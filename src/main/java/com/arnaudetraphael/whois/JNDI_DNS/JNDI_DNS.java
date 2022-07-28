/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arnaudetraphael.whois.JNDI_DNS;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author arnau
 */

public class JNDI_DNS {
    private static java.util.Properties _p = new java.util.Properties();
    private static DirContext dc;
    private static String[] attrIDs = new String[] { "A", "NS", "MX", "AAAA", "CNAME", "SOA", "PTR", "HINFO", "NAPTR",
            "SRV", "TXT" };
    private static String adresse = "";
    private static InitialContext _ic ; 
    private static NamingEnumeration<?> ne;
    public JNDI_DNS() {
        try {

            System.out.println(adresse);

            _p.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");

            dc = new InitialDirContext(_p);

            // Get all the attributes of named object

            // System.out.println(Arrays.toString(attrIDs));
            

            // Get all Web address suffixes:
            _ic = new InitialContext(_p);
            ne = _ic.list("");
            //getWebSuffixes(_ic, ne);

            NameParser np = _ic.getNameParser(_ic.getNameInNamespace());
            Name university_of_Mondragon = np.parse("www.mondragon.edu");
            //System.out.print("\nwww.mondragon.edu has " + university_of_Mondragon.size() + " components:");
            /*for (int i = 0; i < university_of_Mondragon.size(); i++) {
                System.out.print("\t" + university_of_Mondragon.get(i));
            }*/
            System.out.println("------------------------");
            Object o = _ic.lookup(university_of_Mondragon.getPrefix(university_of_Mondragon.size() - 1));
            // System.out.println(university_of_Mondragon.get(0)); //prends en préfix edu
            ne = (((com.sun.jndi.dns.DnsContext) o).getAttributes(university_of_Mondragon, null)).getAll();
            while (ne.hasMore()) {
                BasicAttribute ba = (BasicAttribute) ne.next();
                System.out.println(ba.get(0));
                System.out.print("\n\tAttribute id. [" + ba.getID() + "]: ");
                NamingEnumeration<?> nee = ba.getAll();
                while (nee.hasMore()) {
                    System.out.print("\t" + nee.next());
                }

            }
            System.out.println("------------------------");
            System.out.println("\n\nClosing...");
            _ic.close();
        } catch (NamingException ne) {
            System.err.println(ne.getMessage() + ": " + ne.getExplanation());
        }
    }

    public static Vector<String> getWebSuffixes(InitialContext _ic, NamingEnumeration<?> ne) throws NamingException {
        Vector<String> v = new Vector<String>();
        
        try {
            System.out.println("\nInitial context: " + _ic.getNameInNamespace());
        } catch (NamingException e) {

            e.printStackTrace();
        }
        while (ne.hasMore()) {
            v.add ("\t" + ((NameClassPair) (ne.next())).getName() );
            System.out.println("\t" + ((NameClassPair) (ne.next())).getName());

        }
        return v;
    }
    
    public static Vector<String> getDnsRecords(String[] attrIDs, DirContext dc, String adresse) throws NamingException {
        
        Vector<String> v = new Vector<String>();

        //System.out.println("ntm jovialement");
        //System.out.println(j);
        System.out.println("Debut");
        for (int i = 0; i < attrIDs.length; i++) {
            Attributes attrs = dc.getAttributes(adresse, new String[] { attrIDs[i] });
            //System.out.println("wouhou je suis rentré");
            if (attrs != null && attrs.size() != 0) {
                //System.out.println("wouhou je suis dans le if ");
                NamingEnumeration<?> test = attrs.get(attrIDs[i]).getAll();
                while (test.hasMoreElements()) {
                    //System.out.println("wouhou je suis dans le while ");
                    v.add ( attrIDs[i] + " : " + test.next().toString());
                    //System.out.println(res);
                    //System.out.println("11111111111111111111");
                   // System.out.println(v.get(j));

                    //System.out.println(j);
                    
                }
                 

            } else {
                //System.out.println("wouhou je suis dans le else ");
                //System.out.println(j);
                //v.add(  attrIDs[i] + " : Pas d'information sur l'attribut" ) ;
                //System.out.println(res);
                //System.out.println("111111111111111111");
                //System.out.println(v.get(j));
                
            }
        }
        System.out.println("Fin");
        // System.out.println(v.get(0));
        // System.out.println(v.get(1));
        // System.out.println(v.get(3));
        // System.out.println(v.get(4));
        
        return v;
    }

    public static java.util.Properties get_p() {
        return _p;
    }

    public static DirContext getDc() {
        return dc;
    }

    public static String[] getAttrIDs() {
        return attrIDs;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static void setAdresse(String adresse) {
        JNDI_DNS.adresse = adresse;
    }

    public static InitialContext get_ic() {
        return _ic;
    }

    public static void set_ic(InitialContext _ic) {
        JNDI_DNS._ic = _ic;
    }

    public static NamingEnumeration<?> getNe() {
        return ne;
    }

    public static void setNe(NamingEnumeration<?> ne) {
        JNDI_DNS.ne = ne;
    }

}
