package org.packt.functional.codes.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service("employeeFileStreamService")
public class EmployeeFileStreamService {
	
	public void viewParallelAllEmps(){
		
	}
	
	public void viewFileContent(String empFile) throws IOException{
		Consumer<String> readStr = System.out::println;
		Stream<String> content = null;
		Path path = Paths.get(empFile);
	   	content = Files.lines(path, Charset.defaultCharset());
	   	content.map(String::toUpperCase)
	   		   .forEach(readStr);
	    content.close();
	  }
	
	public long countRecsInFile(String empFile) throws IOException{
		long numWords = 0;
		Stream<String> content = null;
		Path path = Paths.get(empFile);
	   	content = Files.lines(path, Charset.defaultCharset());
		numWords = content
				.map(line -> Arrays.stream(line.split(" ")))
				.count();
       	content.close();
       	return numWords;
    }
	
	public String searchFilesDir(String filePath, String extension) throws IOException{
		Path root = Paths.get(filePath);
		int depth = 3;
		Stream<Path> searchStream = Files.find(root, depth, (path, attr) ->
		        String.valueOf(path).endsWith(extension));
		String found = searchStream
		        .sorted()
		        .map(String::valueOf)
		        .collect(Collectors.joining(" / "));
		searchStream.close();
		return found;
	}
	
	public void viewDirFiles(String dirPath, String extension) throws IOException{
		Consumer<String> readStr = System.out::println;
		Stream<Path> stream = Files.list(Paths.get(dirPath)).sequential();
		stream.map(String::valueOf)
		        .filter(path -> path.endsWith(extension))
		        .forEach(readStr);
	}
	
	public void viewDirBuffered(String dirPath) throws IOException{
		Consumer<String> readStr = System.out::println;
		BufferedReader buff = Files.newBufferedReader(Paths.get(dirPath));
		buff.lines()
		     .map(String::toLowerCase)
		     .forEach(readStr);
	}
	
	public long countRecByAge(String filePath, int age) throws IOException{
		Path path = Paths.get(filePath);
		BufferedReader reader = Files.newBufferedReader(path);
		long numRec = reader
		        .lines()
		        .filter(line -> line.contains(age+""))
		        .count();
		return numRec;
	}
   

}
