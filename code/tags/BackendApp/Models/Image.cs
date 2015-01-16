using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace BackendApp.Models
{
   //The properties of the resources are added to the model class
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