package com.my.tikaparser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ContentHandler;
import java.util.Arrays;


import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;



public class MyContentParser {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws FileNotFoundException, IOException, SAXException, TikaException {


		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("No file found. Exiting!,,,");
			System.exit(0);
		}

		Detector detector = new DefaultDetector();
		System.out.println("File Parsing Starts");
		try (InputStream str = new BufferedInputStream(
				new FileInputStream(
						new File(file.toPath().toUri())))) {
			if (str == null) {
				System.out.println("No Stream found! Exiting");
				System.exit(0);
			}
			parseMetaData(str);
		}

		System.out.println("PARSING FINISHED");

		System.out.println("NORMAL PNG STARTED!");

	}

	private static void parseMetaData(InputStream str) {
		Parser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		ParseContext context = new ParseContext();
		try {
		parser.parse(str, handler, metadata, context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(metadata.toString());
	}
}
