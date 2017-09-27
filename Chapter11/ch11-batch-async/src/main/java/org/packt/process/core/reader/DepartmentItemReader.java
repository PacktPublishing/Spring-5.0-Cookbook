package org.packt.process.core.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.packt.process.core.model.data.Department;
import org.packt.process.core.model.data.Departments;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;

public class DepartmentItemReader implements ItemReader<Department> {
    private final String filename;
    private ItemReader<Department> delegate;

    public DepartmentItemReader(final String filename) {
        this.filename = filename;
    }

    @Override
    public Department read() throws Exception {
        if (delegate == null) {
            delegate = new IteratorItemReader<>(depts());
        }
        return delegate.read();
    }

    private List<Department> depts() throws FileNotFoundException, JAXBException {
       JAXBContext context = JAXBContext.newInstance(Departments.class, Department.class);
       Unmarshaller unmarshaller = context.createUnmarshaller();
       Departments deptList = (Departments) unmarshaller.unmarshal(new FileInputStream(filename));
       return deptList.getDepartment();
             
    }
}
