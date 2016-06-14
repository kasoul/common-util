package com.spuerh.hz.bigdata.util.nlp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
/*import org.wltea.analyzer.lucene.IKAnalyzer;*/
import org.wltea.analyzer.dic.Dictionary;

/**
 * 功能说明：WordAnalyzer分词工具
 * huangchaohz
 */
public class WordAnalyzer {

	public static void main(String[] args) {
		String str = "最希望从企业得到的是独家的内容或销售信息，" + "获得打折或促销信息等；最不希望企业进行消息或广告" + "轰炸及访问用户的个人信息等。这值得使用社会化媒体" + "的企业研究";

		ikAnalysis(str);
	}

	public static String ikAnalysis(String str) {
		StringBuffer sb = new StringBuffer();
		try {
			// InputStream in = new FileInputStream(str);//str为文件路径
			byte[] bt = str.getBytes();// str为文字字符串
			InputStream ip = new ByteArrayInputStream(bt);
			Reader read = new InputStreamReader(ip);
			Dictionary dic = Dictionary.initial(null); // 暂无词典，无法生成
			IKSegmenter iks = new IKSegmenter(read, null);// 暂无词典，无法生成
			Lexeme t;
			while ((t = iks.next()) != null) {
				sb.append(t.getLexemeText() + ",");

			}
			sb.delete(sb.length() - 1, sb.length());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	//@Deprecated
	/*public static void ikAnalysisWithLucene(String str) {
		// 创建分词对象
		Analyzer anal = new IKAnalyzer(true);
		StringReader reader = new StringReader(str);
		// 分词
		TokenStream ts = null;
		ts = anal.tokenStream("", reader);
		CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
		// 遍历分词数据
		try {
			while (ts.incrementToken()) {
				System.out.print(term.toString() + "|");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader.close();
		System.out.println();
	}
	*/
}
