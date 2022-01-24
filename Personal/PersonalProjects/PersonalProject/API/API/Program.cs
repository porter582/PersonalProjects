using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Text.Json;

namespace API
{
    class Program
    {
        public class MyResponse
        {
            [JsonProperty(PropertyName = "page")]
            public int page { get; set; }
            [JsonProperty(PropertyName = "per_page")]
            public int per_page { get; set; }
            [JsonProperty(PropertyName = "total")]
            public int total { get; set; }
            [JsonProperty(PropertyName = "total_pages")]
            public int total_pages { get; set; }
            [JsonProperty(PropertyName = "data")]
            public Data[] data { get; set; }
        }

        public class Data
        {
            [JsonProperty(PropertyName = "Title")]
            public string Title { get; set; }
            [JsonProperty(PropertyName = "Year")]
            public int Year { get; set; }
            [JsonProperty(PropertyName = "imdbID")]
            public string imdbID { get; set; }
        }
        static void Main(string[] args)
        {
            getMovieTitles("spiderman");
        }

        static MyResponse getRequest(string substr, int page)
        {
            string html = string.Empty;
            string url = @"https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&page=" + page;

            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.AutomaticDecompression = DecompressionMethods.GZip;

            using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
            using (Stream stream = response.GetResponseStream())
            using (StreamReader reader = new StreamReader(stream))
            {
                html = reader.ReadToEnd();
            }
            MyResponse myResponse = new MyResponse();
            myResponse = JsonConvert.DeserializeObject<MyResponse>(html);
            return myResponse;
        }

        static string[] getMovieTitles(string substr)
        {
            List<MyResponse> myResponse = new List<MyResponse>();
            List<Data> listData = new List<Data>();
            myResponse.Add(getRequest(substr, 1));

            if(myResponse[0].page > 0)
            {
                for (int i = 2; i <= myResponse[0].total_pages; i++)
                {
                    myResponse.Add(getRequest(substr, i));
                }
            }

            for (int i = 0; i < myResponse.Count; i++)
            {
                listData.AddRange(myResponse[i].data);
            }

            string[] results = new string[listData.Count];
            for (int i = 0; i < results.Length; i++)
            {
                results[i] = listData[i].Title;
            }
            Array.Sort(results);

            return results;
        }
    }
}
