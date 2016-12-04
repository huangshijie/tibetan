package com.huang.TibetanLibrary.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FontUtil {
	
	public static Map<String, String>  PREFIXSET = new HashMap<String, String>();
	static{
		PREFIXSET.put("ག", "f42");
		PREFIXSET.put("ད", "f51");
		PREFIXSET.put("བ", "f56");
		PREFIXSET.put("མ", "f58");
		PREFIXSET.put("འ", "f60");
	}
	
	public static Map<String, String> SUPERSCRIPTSET = new HashMap<String, String>();
	static{
		SUPERSCRIPTSET.put("ར", "f62");
		SUPERSCRIPTSET.put("ལ", "f63");
		SUPERSCRIPTSET.put("ས", "f66");
	}
	
	public static Map<String, String> VOWELSET= new HashMap<String, String>();
	static{
		VOWELSET.put("ི", "i");
		VOWELSET.put("ུ", "u");
		VOWELSET.put("ེ", "e");
		VOWELSET.put("ོ", "o");
	}
	
	public static Map<String, String> SUBSCRIPTSET = new HashMap<String, String>();
	static{
		SUBSCRIPTSET.put("འ", "v");
		SUBSCRIPTSET.put("ཡ", "y");
		SUBSCRIPTSET.put("ར", "r");
		SUBSCRIPTSET.put("ལ", "l");
		SUBSCRIPTSET.put("ཝ", "w");
		SUBSCRIPTSET.put("གྲྷ", "");
	}
	
	public static Map<String, String> INTERNATIONALPHONETICALPHABETSET = new HashMap<String, String>();
	static{
		INTERNATIONALPHONETICALPHABETSET.put("ཀ", "k");
		INTERNATIONALPHONETICALPHABETSET.put("ཁ", "kh");
		INTERNATIONALPHONETICALPHABETSET.put("ག", "ɡ");
		INTERNATIONALPHONETICALPHABETSET.put("ང", "ŋ");
		INTERNATIONALPHONETICALPHABETSET.put("ཅ", "ʨ");
		INTERNATIONALPHONETICALPHABETSET.put("ཆ", "ʨh");
		INTERNATIONALPHONETICALPHABETSET.put("ཇ", "ʤ");
		INTERNATIONALPHONETICALPHABETSET.put("ཉ", "ȵ");
		INTERNATIONALPHONETICALPHABETSET.put("ཏ", "t");
		INTERNATIONALPHONETICALPHABETSET.put("ཐ", "th");
		INTERNATIONALPHONETICALPHABETSET.put("ད", "d");
		INTERNATIONALPHONETICALPHABETSET.put("ན", "n");
		INTERNATIONALPHONETICALPHABETSET.put("ྷ", "h");		
		INTERNATIONALPHONETICALPHABETSET.put("པ", "p");
		INTERNATIONALPHONETICALPHABETSET.put("ཕ", "ph");
		INTERNATIONALPHONETICALPHABETSET.put("བ", "b");
		INTERNATIONALPHONETICALPHABETSET.put("མ", "m");
		INTERNATIONALPHONETICALPHABETSET.put("ཙ", "ʦ");
		INTERNATIONALPHONETICALPHABETSET.put("ཚ", "ʦh");
		INTERNATIONALPHONETICALPHABETSET.put("ཛ", "ʣ");
		INTERNATIONALPHONETICALPHABETSET.put("ཝ", "w");
		INTERNATIONALPHONETICALPHABETSET.put("ཞ", "ʑ");
		INTERNATIONALPHONETICALPHABETSET.put("ཟ", "z");
		INTERNATIONALPHONETICALPHABETSET.put("འ", "");
		INTERNATIONALPHONETICALPHABETSET.put("ཡ", "j");
		INTERNATIONALPHONETICALPHABETSET.put("ར", "r");
		INTERNATIONALPHONETICALPHABETSET.put("ལ", "l");
		INTERNATIONALPHONETICALPHABETSET.put("ཤ", "");
		INTERNATIONALPHONETICALPHABETSET.put("ས", "s");
		INTERNATIONALPHONETICALPHABETSET.put("ཧ", "h");
		INTERNATIONALPHONETICALPHABETSET.put("ཨ", "");
		INTERNATIONALPHONETICALPHABETSET.put("ི", "i");
		INTERNATIONALPHONETICALPHABETSET.put("ུ", "u");
		INTERNATIONALPHONETICALPHABETSET.put("ེ", "e");
		INTERNATIONALPHONETICALPHABETSET.put("ོ", "o");
	}
	
	public static Map<String, String> WILLIESET = new HashMap<String, String>();
	static{
		WILLIESET.put("ཀ", "k");
		WILLIESET.put("ཁ", "kh");
		WILLIESET.put("ྒ", "ɡ");
		WILLIESET.put("ག", "ɡ");
		WILLIESET.put("ང", "ng");
		WILLIESET.put("ཅ", "c");
		WILLIESET.put("ཆ", "ch");
		WILLIESET.put("ཇ", "j");
		WILLIESET.put("ཉ", "ny");
		WILLIESET.put("ཏ", "t");
		WILLIESET.put("ཐ", "th");
		WILLIESET.put("ད", "d");
		WILLIESET.put("ན", "n");
		WILLIESET.put("ྷ", "h");
		WILLIESET.put("པ", "p");
		WILLIESET.put("ཕ", "ph");
		WILLIESET.put("བ", "b");
		WILLIESET.put("མ", "m");
		WILLIESET.put("ཙ", "ʦ");
		WILLIESET.put("ཚ", "ʦh");
		WILLIESET.put("ཛ", "ʣ");
		WILLIESET.put("ྭ	", "w");		
		WILLIESET.put("ཝ", "w");
		WILLIESET.put("ཞ", "zh");
		WILLIESET.put("ཟ", "z");
		WILLIESET.put("འ", "v");
		WILLIESET.put("ཡ", "y");
		WILLIESET.put("ྱ", "y");
		WILLIESET.put("ྲ", "r");
		WILLIESET.put("ར", "r");
		WILLIESET.put("ླ", "l");
		WILLIESET.put("ལ", "l");
		WILLIESET.put("ཤ", "sh");
		WILLIESET.put("ས", "s");
		WILLIESET.put("ཧ", "h");
		WILLIESET.put("ཨ", "");
		WILLIESET.put("ི", "i");
		WILLIESET.put("ུ", "u");
		WILLIESET.put("ེ", "e");
		WILLIESET.put("ོ", "o");
		WILLIESET.put("ྭ", "w");
	}
	
	public static Set<String> EXHAUSTIVESET = new HashSet<String>();
	static{
		EXHAUSTIVESET.add("ka");
		EXHAUSTIVESET.add("kwag");
		EXHAUSTIVESET.add("kyags");
		EXHAUSTIVESET.add("krang");
		EXHAUSTIVESET.add("klangs");
		EXHAUSTIVESET.add("dkad");
		EXHAUSTIVESET.add("dkyan");
		EXHAUSTIVESET.add("dkrand");
		EXHAUSTIVESET.add("bkab");
		EXHAUSTIVESET.add("bkyabs");
		EXHAUSTIVESET.add("bkram");
		EXHAUSTIVESET.add("bklams");
		EXHAUSTIVESET.add("rkar");
		EXHAUSTIVESET.add("rkyard");
		EXHAUSTIVESET.add("lkal");
		EXHAUSTIVESET.add("skald");
		EXHAUSTIVESET.add("skyas");
		EXHAUSTIVESET.add("skravo");
		EXHAUSTIVESET.add("brkavi");
		EXHAUSTIVESET.add("brkyavu");
		EXHAUSTIVESET.add("bski");
		EXHAUSTIVESET.add("bskyig");
		EXHAUSTIVESET.add("bskrigs");
		EXHAUSTIVESET.add("khing");
		EXHAUSTIVESET.add("khwings");
		EXHAUSTIVESET.add("khyid");
		EXHAUSTIVESET.add("khrin");
		EXHAUSTIVESET.add("mkhind");
		EXHAUSTIVESET.add("mkhyib");
		EXHAUSTIVESET.add("mkhribs");
		EXHAUSTIVESET.add("vkhim");
		EXHAUSTIVESET.add("vkhyims");
		EXHAUSTIVESET.add("vkhrir");
		EXHAUSTIVESET.add("gird");
		EXHAUSTIVESET.add("gwil");
		EXHAUSTIVESET.add("gyild");
		EXHAUSTIVESET.add("gris");
		EXHAUSTIVESET.add("grwivo");
		EXHAUSTIVESET.add("glivi");
		EXHAUSTIVESET.add("dgivu");
		EXHAUSTIVESET.add("dgyu");
		EXHAUSTIVESET.add("dgrug");
		EXHAUSTIVESET.add("bgugs");
		EXHAUSTIVESET.add("bgyung");
		EXHAUSTIVESET.add("bgrungs");
		EXHAUSTIVESET.add("mgud");
		EXHAUSTIVESET.add("mgyun");
		EXHAUSTIVESET.add("mgrund");
		EXHAUSTIVESET.add("vgub");
		EXHAUSTIVESET.add("vgyubs");
		EXHAUSTIVESET.add("vgrum");
		EXHAUSTIVESET.add("rgums");
		EXHAUSTIVESET.add("rgyur");
		EXHAUSTIVESET.add("lgurd");
		EXHAUSTIVESET.add("sgul");
		EXHAUSTIVESET.add("sgyuld");
		EXHAUSTIVESET.add("sgrus");
		EXHAUSTIVESET.add("brguvo");
		EXHAUSTIVESET.add("brgyuvi");
		EXHAUSTIVESET.add("bsguvu");
		EXHAUSTIVESET.add("bsgye");
		EXHAUSTIVESET.add("bsgreg");
		EXHAUSTIVESET.add("ngegs");
		EXHAUSTIVESET.add("dngeng");
		EXHAUSTIVESET.add("mngengs");
		EXHAUSTIVESET.add("rnged");
		EXHAUSTIVESET.add("lngen");
		EXHAUSTIVESET.add("sngend");
		EXHAUSTIVESET.add("brngeb");
		EXHAUSTIVESET.add("bsngebs");
		EXHAUSTIVESET.add("cem");
		EXHAUSTIVESET.add("gcems");
		EXHAUSTIVESET.add("bcer");
		EXHAUSTIVESET.add("lcerd");
		EXHAUSTIVESET.add("chel");
		EXHAUSTIVESET.add("mcheld");
		EXHAUSTIVESET.add("vches");
		EXHAUSTIVESET.add("jevo");
		EXHAUSTIVESET.add("mjevi");
		EXHAUSTIVESET.add("vjevu");
		EXHAUSTIVESET.add("rjo");
		EXHAUSTIVESET.add("ljog");
		EXHAUSTIVESET.add("brjogs");
		EXHAUSTIVESET.add("nyong");
		EXHAUSTIVESET.add("nywongs");
		EXHAUSTIVESET.add("gnyod");
		EXHAUSTIVESET.add("mnyon");
		EXHAUSTIVESET.add("rnyond");
		EXHAUSTIVESET.add("snyob");
		EXHAUSTIVESET.add("brnyobs");
		EXHAUSTIVESET.add("bsnyom");
		EXHAUSTIVESET.add("toms");
		EXHAUSTIVESET.add("tror");
		EXHAUSTIVESET.add("gtord");
		EXHAUSTIVESET.add("btol");
		EXHAUSTIVESET.add("rtold");
		EXHAUSTIVESET.add("ltos");
		EXHAUSTIVESET.add("stovo");
		EXHAUSTIVESET.add("brtovi");
		EXHAUSTIVESET.add("bltovu");
		EXHAUSTIVESET.add("k");
		EXHAUSTIVESET.add("kw");
		EXHAUSTIVESET.add("ky");
		EXHAUSTIVESET.add("kr");
		EXHAUSTIVESET.add("kl");
		EXHAUSTIVESET.add("dk");
		EXHAUSTIVESET.add("dky");
		EXHAUSTIVESET.add("dkr");
		EXHAUSTIVESET.add("bk");
		EXHAUSTIVESET.add("bky");
		EXHAUSTIVESET.add("bkr");
		EXHAUSTIVESET.add("bkl");
		EXHAUSTIVESET.add("rk");
		EXHAUSTIVESET.add("rky");
		EXHAUSTIVESET.add("lk");
		EXHAUSTIVESET.add("sk");
		EXHAUSTIVESET.add("sky");
		EXHAUSTIVESET.add("skr");
		EXHAUSTIVESET.add("brk");
		EXHAUSTIVESET.add("brky");
		EXHAUSTIVESET.add("bsk");
		EXHAUSTIVESET.add("bsky");
		EXHAUSTIVESET.add("bskr");
		EXHAUSTIVESET.add("kh");
		EXHAUSTIVESET.add("khw");
		EXHAUSTIVESET.add("khy");
		EXHAUSTIVESET.add("khr");
		EXHAUSTIVESET.add("mkh");
		EXHAUSTIVESET.add("mkhy");
		EXHAUSTIVESET.add("mkhr");
		EXHAUSTIVESET.add("vkh");
		EXHAUSTIVESET.add("vkhy");
		EXHAUSTIVESET.add("vkhr");
		EXHAUSTIVESET.add("g");
		EXHAUSTIVESET.add("gw");
		EXHAUSTIVESET.add("gy");
		EXHAUSTIVESET.add("gr");
		EXHAUSTIVESET.add("grw");
		EXHAUSTIVESET.add("gl");
		EXHAUSTIVESET.add("dg");
		EXHAUSTIVESET.add("dgy");
		EXHAUSTIVESET.add("dgr");
		EXHAUSTIVESET.add("bg");
		EXHAUSTIVESET.add("bgy");
		EXHAUSTIVESET.add("bgr");
		EXHAUSTIVESET.add("mg");
		EXHAUSTIVESET.add("mgy");
		EXHAUSTIVESET.add("mgr");
		EXHAUSTIVESET.add("vg");
		EXHAUSTIVESET.add("vgy");
		EXHAUSTIVESET.add("vgr");
		EXHAUSTIVESET.add("rg");
		EXHAUSTIVESET.add("rgy");
		EXHAUSTIVESET.add("lg");
		EXHAUSTIVESET.add("sg");
		EXHAUSTIVESET.add("sgy");
		EXHAUSTIVESET.add("sgr");
		EXHAUSTIVESET.add("brg");
		EXHAUSTIVESET.add("brgy");
		EXHAUSTIVESET.add("bsg");
		EXHAUSTIVESET.add("bsgy");
		EXHAUSTIVESET.add("bsgr");
		EXHAUSTIVESET.add("ng");
		EXHAUSTIVESET.add("dng");
		EXHAUSTIVESET.add("mng");
		EXHAUSTIVESET.add("rng");
		EXHAUSTIVESET.add("lng");
		EXHAUSTIVESET.add("sng");
		EXHAUSTIVESET.add("brng");
		EXHAUSTIVESET.add("bsng");
		EXHAUSTIVESET.add("c");
		EXHAUSTIVESET.add("gc");
		EXHAUSTIVESET.add("bc");
		EXHAUSTIVESET.add("lc");
		EXHAUSTIVESET.add("ch");
		EXHAUSTIVESET.add("mch");
		EXHAUSTIVESET.add("vch");
		EXHAUSTIVESET.add("j");
		EXHAUSTIVESET.add("mj");
		EXHAUSTIVESET.add("vj");
		EXHAUSTIVESET.add("rj");
		EXHAUSTIVESET.add("lj");
		EXHAUSTIVESET.add("brj");
		EXHAUSTIVESET.add("ny");
		EXHAUSTIVESET.add("nyw");
		EXHAUSTIVESET.add("gny");
		EXHAUSTIVESET.add("mny");
		EXHAUSTIVESET.add("rny");
		EXHAUSTIVESET.add("sny");
		EXHAUSTIVESET.add("brny");
		EXHAUSTIVESET.add("bsny");
		EXHAUSTIVESET.add("t");
		EXHAUSTIVESET.add("tr");
		EXHAUSTIVESET.add("gt");
		EXHAUSTIVESET.add("bt");
		EXHAUSTIVESET.add("rt");
		EXHAUSTIVESET.add("lt");
		EXHAUSTIVESET.add("st");
		EXHAUSTIVESET.add("brt");
		EXHAUSTIVESET.add("blt");
		EXHAUSTIVESET.add("bst");
		EXHAUSTIVESET.add("th");
		EXHAUSTIVESET.add("mth");
		EXHAUSTIVESET.add("vth");
		EXHAUSTIVESET.add("d");
		EXHAUSTIVESET.add("dw");
		EXHAUSTIVESET.add("dr");
		EXHAUSTIVESET.add("drw");
		EXHAUSTIVESET.add("gd");
		EXHAUSTIVESET.add("bd");
		EXHAUSTIVESET.add("md");
		EXHAUSTIVESET.add("vd");
		EXHAUSTIVESET.add("vdr");
		EXHAUSTIVESET.add("rd");
		EXHAUSTIVESET.add("ld");
		EXHAUSTIVESET.add("sd");
		EXHAUSTIVESET.add("brd");
		EXHAUSTIVESET.add("bld");
		EXHAUSTIVESET.add("bsd");
		EXHAUSTIVESET.add("n");
		EXHAUSTIVESET.add("gn");
		EXHAUSTIVESET.add("mn");
		EXHAUSTIVESET.add("rn");
		EXHAUSTIVESET.add("sn");
		EXHAUSTIVESET.add("brn");
		EXHAUSTIVESET.add("bsn");
		EXHAUSTIVESET.add("p");
		EXHAUSTIVESET.add("pr");
		EXHAUSTIVESET.add("dp");
		EXHAUSTIVESET.add("dpy");
		EXHAUSTIVESET.add("dpr");
		EXHAUSTIVESET.add("lp");
		EXHAUSTIVESET.add("sp");
		EXHAUSTIVESET.add("spy");
		EXHAUSTIVESET.add("spr");
		EXHAUSTIVESET.add("ph");
		EXHAUSTIVESET.add("phy");
		EXHAUSTIVESET.add("phyw");
		EXHAUSTIVESET.add("phr");
		EXHAUSTIVESET.add("vph");
		EXHAUSTIVESET.add("vphy");
		EXHAUSTIVESET.add("vphr");
		EXHAUSTIVESET.add("b");
		EXHAUSTIVESET.add("by");
		EXHAUSTIVESET.add("br");
		EXHAUSTIVESET.add("bl");
		EXHAUSTIVESET.add("db");
		EXHAUSTIVESET.add("dby");
		EXHAUSTIVESET.add("dbr");
		EXHAUSTIVESET.add("vb");
		EXHAUSTIVESET.add("vby");
		EXHAUSTIVESET.add("vbr");
		EXHAUSTIVESET.add("rb");
		EXHAUSTIVESET.add("lb");
		EXHAUSTIVESET.add("sb");
		EXHAUSTIVESET.add("sby");
		EXHAUSTIVESET.add("sbr");
		EXHAUSTIVESET.add("m");
		EXHAUSTIVESET.add("my");
		EXHAUSTIVESET.add("dm");
		EXHAUSTIVESET.add("dmy");
		EXHAUSTIVESET.add("rm");
		EXHAUSTIVESET.add("rmy");
		EXHAUSTIVESET.add("sm");
		EXHAUSTIVESET.add("smy");
		EXHAUSTIVESET.add("smr");
		EXHAUSTIVESET.add("ts");
		EXHAUSTIVESET.add("gts");
		EXHAUSTIVESET.add("bts");
		EXHAUSTIVESET.add("rts");
		EXHAUSTIVESET.add("rtsw");
		EXHAUSTIVESET.add("sts");
		EXHAUSTIVESET.add("brts");
		EXHAUSTIVESET.add("bsts");
		EXHAUSTIVESET.add("tsh");
		EXHAUSTIVESET.add("tshw");
		EXHAUSTIVESET.add("mtsh");
		EXHAUSTIVESET.add("vtsh");
		EXHAUSTIVESET.add("dz");
		EXHAUSTIVESET.add("mdz");
		EXHAUSTIVESET.add("vdz");
		EXHAUSTIVESET.add("rdz");
		EXHAUSTIVESET.add("brdz");
		EXHAUSTIVESET.add("w");
		EXHAUSTIVESET.add("zh");
		EXHAUSTIVESET.add("zhw");
		EXHAUSTIVESET.add("gzh");
		EXHAUSTIVESET.add("bzh");
		EXHAUSTIVESET.add("z");
		EXHAUSTIVESET.add("zw");
		EXHAUSTIVESET.add("zl");
		EXHAUSTIVESET.add("gz");
		EXHAUSTIVESET.add("bz");
		EXHAUSTIVESET.add("bzl");
		EXHAUSTIVESET.add("v");
		EXHAUSTIVESET.add("y");
		EXHAUSTIVESET.add("gy");
		EXHAUSTIVESET.add("r");
		EXHAUSTIVESET.add("rw");
		EXHAUSTIVESET.add("rl");
		EXHAUSTIVESET.add("brl");
		EXHAUSTIVESET.add("l");
		EXHAUSTIVESET.add("lw");
		EXHAUSTIVESET.add("sh");
		EXHAUSTIVESET.add("shw");
		EXHAUSTIVESET.add("gsh");
		EXHAUSTIVESET.add("bsh");
		EXHAUSTIVESET.add("s");
		EXHAUSTIVESET.add("sr");
		EXHAUSTIVESET.add("sl");
		EXHAUSTIVESET.add("gs");
		EXHAUSTIVESET.add("bs");
		EXHAUSTIVESET.add("bsw");
		EXHAUSTIVESET.add("bsr");
		EXHAUSTIVESET.add("bsl");
		EXHAUSTIVESET.add("h");
		EXHAUSTIVESET.add("hw");
		EXHAUSTIVESET.add("hr");
		EXHAUSTIVESET.add("lh");
	}
	
}
