import java.util.HashMap;

/*
    You are working on the Amazon Devices team and need to create unique device names to be used in a residential loT (Internet of Things) system.
    If a device name already exists in the system, an integer number is added at the end of the name to make it unique.
    The integer added starts with land is incremented by 1 for each new request of an existing device name.

    Given a list of device name requests, write an algorithm to process all the requests and output a list of the corresponding unique device names.

    Input
    The input to the function/method consists of two arguments:
    num, an integer representing the number of device names;
    devicenames, a list of strings representing the device names;

    Output
    Return a list of strings representing the usernames in the order assigned

    Constraints
    1 <= num <= 10^5
    1 <= length of devicenames[i] >= 20
    0 <= i < num

    Note
    devicenames contains only lowercase English letters in the range ASCII[a-z].

    Example
    Input:
    num = 6
    devicenames = ["switch", "tv", "switch", "tv","switch", "tv"]

    Output:
    ["switch","tv","switch1", "tv1", "switch2", "tv2"]

    Explanation:
    devicenames[0] = "switch" is unique. uniqueDevicename[0]="switch" devicenames[1] = "tv" is unique. uniqueDevicename[1]="tv"
    devicenames[2] = devicenames[0]. Add 1 at the end the previous unique username "switch", uniqueDevicename[2]="switch1"
    devicenames[3] = devicenames[1]. Add 1 at the end the previous unique username "tv", uniqueDevicename[3]="tv1"
    devicenames[4] = devicenames[2]. Increment by 1 the number at the end of the previous unique username "switch1", uniqueDevicenames[4]="switch2"
    devicenames[5] = devicenames[3]. Increment by 1 the number at the end of the previous unique username "tv1". uniqueDevicenames[5]="tv2"
    return unique Devicenames = ["switch", "tv" "switch1" "tv1", "switch2", "tv2"]
*/
public class UniqueDeviceNames {

    public void Test() {

        String[] result = Perform(6, new String[] { "switch", "tv", "switch", "tv", "switch", "tv" });
        DisplayResult(result);
    }

    public void DisplayResult(String[] result) {
        for (String deviceName : result)
            System.out.print(deviceName + " ");
        System.out.println("");
    }

    /*
     * Time Complexity: O(n), where n refers to size of deviceNames array.
     * 
     * Space Complexity: O(n) + O(n). The first O(n) refers to constructing the
     * resultant string array. The other O(n) refers to maintaining the unique
     * string keys and their number of occurances (as a hash map).
     */
    public String[] Perform(int num, String[] deviceNames) {
        HashMap<String, Integer> helperMap = new HashMap<String, Integer>();

        String[] result = new String[num];

        for (int nameIndex = 0; nameIndex < deviceNames.length; nameIndex++) {
            String deviceName = deviceNames[nameIndex];
            if (!helperMap.containsKey(deviceName)) {
                helperMap.put(deviceName, 1);
                result[nameIndex] = deviceName;
            } else {
                result[nameIndex] = deviceName + helperMap.get(deviceName);
                helperMap.put(deviceName, helperMap.get(deviceName) + 1);
            }
        }

        return result;
    }
}
