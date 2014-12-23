using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace BackendApp.Models
{
   
    public class Image
    {
        public string Id { get; set; }
        public string Name { get; set; }
        public string Path { get; set; }
    }
    
    public class ImageCount
    {
        public int Number { get; set; }
    }
}