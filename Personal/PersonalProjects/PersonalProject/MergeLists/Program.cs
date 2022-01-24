using System;
using System.Collections.Generic;
using System.Linq;

namespace MergeLists
{
    public class Program
    {
        public class ListNode
        {
            public int val;
            public ListNode next;

            public void addListNode(int value)
            {
                ListNode temp = new ListNode();
                temp.val = value;
                temp.next = next;
                next = temp;
            }
        }

        static void Main(string[] args)
        {
            Program program = new Program();
            ListNode list1 = new ListNode() { val = 1};
            list1.addListNode(3);
            list1.addListNode(2);

            ListNode list2 = new ListNode() { val = 2};
            list2.addListNode(4);
            list2.addListNode(3);

            program.MergeTwoLists(list1, list2, program);
            //merge two lists

            int[] dup = new int[5];
            dup[0] = 1;
            dup[1] = 2;
            dup[2] = 3;
            dup[3] = 5;
            dup[4] = 4;

            bool test = program.ContainsDuplicates(dup);
            //check for duplicates in array


        }

        public bool ContainsDuplicates(int[] arr)
        {
            Array.Sort(arr);
            Dictionary<int, int> keyValuePairs = new Dictionary<int, int>();

            for (int i = 0; i < arr.Length; i++)
            {
                if(keyValuePairs.ContainsKey(arr[i]))
                {
                    keyValuePairs[arr[i]] += 1;
                    return true;
                }
                else
                {
                    keyValuePairs.Add(arr[i], 1);
                }
            }
            return false;
        }

        public ListNode MergeTwoLists(ListNode list1, ListNode list2, Program program)
        {
            ListNode returnList = new ListNode();
            ListNode node = returnList;
            while (list1 != null || list2 != null)
            {
                if(list1 != null && list2 != null && list1.val < list2.val)
                {
                    node.next = list1;
                    list1 = list1.next;
                }
                else
                {
                    node.next = list2;
                    list2 = list2.next;
                }
                node = node.next;
            }
            return returnList;
        }
    }
}
