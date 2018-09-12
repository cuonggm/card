package com.cuong.importers;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;

import com.cuong.models.List;
import com.cuong.models.Word;
import com.cuong.service.ListService;
import com.cuong.service.WordService;
import com.cuong.service.impl.ListServiceImpl;
import com.cuong.service.impl.WordServiceImpl;

public class TextFileImporter {

	private static final Logger LOGGER = Logger.getLogger(TextFileImporter.class.getName());

	private ListService listService = new ListServiceImpl();
	private WordService wordService = new WordServiceImpl();

	public boolean importData(File file) {
		try {
			Scanner scanner = new Scanner(file);
			List list = new List();
			list.setName(FilenameUtils.removeExtension(file.getName()));
			LOGGER.info("save list: " + list.getName());
			listService.save(list);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				LOGGER.info("Reading: " + line);
				line = line.trim();
				if (line.isEmpty()) {
					continue;
				}
				String[] parts = line.split("\t");
				if (parts.length >= 3) {
					LOGGER.info("new Word");
					Word word = new Word();
					LOGGER.info("part0" + parts[0]);
					word.setKanji(parts[0].trim());
					LOGGER.info("part1" + parts[1]);
					word.setHiragara(parts[1].trim());
					LOGGER.info("part2" + parts[2]);
					word.setMeaning(parts[2].trim());
					if (parts.length >= 4) {
						LOGGER.info("part3" + parts[3]);
						word.setAmHanViet(parts[3]);
					}
					LOGGER.info("save");
					wordService.save(word);
					LOGGER.info("add word to list");
					list.getWords().add(word);
					LOGGER.info("update list");
					listService.update(list);
				}
			}
			scanner.close();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
			return false;
		}
		return true;
	}

}
