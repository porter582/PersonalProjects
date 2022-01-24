using System;
using System.Collections.Generic;

namespace _250MinuteTest
{
    class Program
    {
        static void Main(string[] args)
        {
            List<int> list = new List<int>();

            list.Add(11);
            list.Add(21);
            list.Add(21);
            list.Add(33);
            list.Add(44);
            list.Add(56);
            list.Add(65);
            list.Add(11);

            countDuplicate(list);
        }

        public static int countDuplicate(List<int> numbers)
        {
            Dictionary<int, int> keyValuePairs = new Dictionary<int, int>();
            int retVal = 0;
            for (int i = 0; i < numbers.Count; i++)
            {
                if(keyValuePairs.ContainsKey(numbers[i]))
                {
                    keyValuePairs[numbers[i]]++;
                    if(keyValuePairs[numbers[i]] <= 2)
                    {
                        retVal++;
                    }
                }
                else
                {
                    keyValuePairs.Add(numbers[i], 1);
                }
            }
            return retVal;
        }
    }
}
