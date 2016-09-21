package com.getwellnetwork.example.hapiXmlBug;


import org.junit.Test;
import org.w3c.dom.Document;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;


/**
 * User:     Kevin Balthaser
 * Contact:  kbalthaser@getwellnetwork.com
 * Date:     9/20/16
 * Time:     2:18 PM
 */
public class SomeClassTest {


    // Sample message pulled from HAPI example code.
    String v25message = "MSH|^~\\&|ULTRA|TML|OLIS|OLIS|200905011130||ORU^R01|20169838-v25|T|2.5\r"
                        + "PID|||7005728^^^TML^MR||TEST^RACHEL^DIAMOND||19310313|F|||200 ANYWHERE ST^^TORONTO^ON^M6G 2T9||(416)888-8888||||||1014071185^KR\r"
                        + "PV1|1||OLIS||||OLIST^BLAKE^DONALD^THOR^^^^^921379^^^^OLIST\r"
                        + "ORC|RE||T09-100442-RET-0^^OLIS_Site_ID^ISO|||||||||OLIST^BLAKE^DONALD^THOR^^^^L^921379\r"
                        + "OBR|0||T09-100442-RET-0^^OLIS_Site_ID^ISO|RET^RETICULOCYTE COUNT^HL79901 literal|||200905011106|||||||200905011106||OLIST^BLAKE^DONALD^THOR^^^^L^921379||7870279|7870279|T09-100442|MOHLTC|200905011130||B7|F||1^^^200905011106^^R\r"
                        + "OBX|1|ST|||Test Value";

    @Test
    public void hapiTestParse() throws  Exception {

        // Create a context
        HapiContext context = new DefaultHapiContext();

        PipeParser pp = context.getPipeParser();
        XMLParser xp = context.getXMLParser();

        // Pipe parse the message, then convert to a DOM Document
        Message msg = pp.parse(v25message);
        Document dom = xp.encodeDocument(msg);

        // We would expect to be able to parse this document back to a HAPI Message
        // This will fail in HAPI 2.2
        Message outmsg = xp.parseDocument(dom, msg.getVersion());
        outmsg.printStructure();
    }

}