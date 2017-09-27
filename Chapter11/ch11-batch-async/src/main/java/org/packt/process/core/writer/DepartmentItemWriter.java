package org.packt.process.core.writer;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.PreDestroy;

import org.packt.process.core.model.data.Department;
import org.springframework.batch.item.ItemWriter;

public class DepartmentItemWriter implements ItemWriter<Department>, Closeable {
    private  PrintWriter writer;

    public DepartmentItemWriter() {
        OutputStream out = null;
        try {
        	out = new FileOutputStream("output.txt");
        } catch (FileNotFoundException e) {
            out = System.out;
        } finally{
        	this.writer = new PrintWriter(out);
        }	
    }

    @Override
    public void write(List<? extends Department> items) throws Exception {
        for (Department item : items) {
            writer.println(item.getName() + " " + item.getDeptid() );
        }
    }

    @PreDestroy
    @Override
    public void close() throws IOException {
        writer.close();
    }
}