package com.bigdatums.avro;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class TestDataGeneration {

    public static void main(String[] args) throws IOException {
        BdPerson p1 = new BdPerson();
        p1.setId(1);
        p1.setUsername("mrscarter");
        p1.setFirstName("Beyonce");
        p1.setLastName("Knowles-Carter");
        p1.setBirthdate("1981-09-04");
        p1.setJoinDate("2016-01-01");
        p1.setPreviousLogins(10000);

        BdPerson p2 = new BdPerson();
        p2.setId(2);
        p2.setUsername("jayz");
        p2.setFirstName("Shawn");
        p2.setMiddleName("Corey");
        p2.setLastName("Carter");
        p2.setBirthdate("1969-12-04");
        p2.setJoinDate("2016-01-01");
        p2.setPreviousLogins(20000);

        File avroOutput = new File("bdperson-test.avro");
        DatumWriter<BdPerson> bdPersonDatumWriter = new SpecificDatumWriter<BdPerson>(BdPerson.class);
        DataFileWriter<BdPerson> dataFileWriter = new DataFileWriter<BdPerson>(bdPersonDatumWriter);
        dataFileWriter.create(p1.getSchema(), avroOutput);
        dataFileWriter.append(p1);
        dataFileWriter.append(p2);
        dataFileWriter.close();

        try {
            DatumReader<BdPerson> bdPersonDatumReader = new SpecificDatumReader(BdPerson.class);
            DataFileReader<BdPerson> dataFileReader = new DataFileReader<BdPerson>(avroOutput, bdPersonDatumReader);
            BdPerson p = null;
            while(dataFileReader.hasNext()){
                p = dataFileReader.next(p);
                System.out.println(p);
            }
        } catch(IOException e) {System.out.println("Error reading Avro");}
    }
}
