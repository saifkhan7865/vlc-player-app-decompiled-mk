package org.bouncycastle.tsp.ers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ERSDirectoryDataGroup extends ERSDataGroup {
    public ERSDirectoryDataGroup(File file) throws FileNotFoundException {
        super(buildGroup(file));
    }

    private static List<ERSData> buildGroup(File file) throws FileNotFoundException {
        Object obj;
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            ArrayList arrayList = new ArrayList(listFiles.length);
            for (int i = 0; i != listFiles.length; i++) {
                if (!listFiles[i].isDirectory()) {
                    obj = new ERSFileData(listFiles[i]);
                } else if (listFiles[i].listFiles().length != 0) {
                    obj = new ERSDirectoryDataGroup(listFiles[i]);
                }
                arrayList.add(obj);
            }
            return arrayList;
        }
        throw new IllegalArgumentException("file reference does not refer to directory");
    }

    public List<ERSFileData> getFiles() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i != this.dataObjects.size(); i++) {
            if (this.dataObjects.get(i) instanceof ERSFileData) {
                arrayList.add((ERSFileData) this.dataObjects.get(i));
            }
        }
        return arrayList;
    }

    public List<ERSDirectoryDataGroup> getSubdirectories() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i != this.dataObjects.size(); i++) {
            if (this.dataObjects.get(i) instanceof ERSDirectoryDataGroup) {
                arrayList.add((ERSDirectoryDataGroup) this.dataObjects.get(i));
            }
        }
        return arrayList;
    }
}
