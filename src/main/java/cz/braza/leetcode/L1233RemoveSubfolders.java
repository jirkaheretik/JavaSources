package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1233. Remove Sub-Folders from the Filesystem
Medium
Topics
Companies
Hint
Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.

If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".

The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.

For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.


Example 1:

Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
Output: ["/a","/c/d","/c/f"]
Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
Example 2:

Input: folder = ["/a","/a/b/c","/a/b/d"]
Output: ["/a"]
Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
 */
public class L1233RemoveSubfolders {
    /*
    First try (after adjustments): sort the array, then remove (not add) all items
    that are subfolders of lastly added item.
    36ms, beats 95.1 %
     */
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> result = new ArrayList<>();
        String last = "XXXX";
        for (String fld: folder)
            if (fld.indexOf(last) != 0) {// not a subfolder
                result.add(fld);
                last = fld + "/";
            }
        return result;
    }

}