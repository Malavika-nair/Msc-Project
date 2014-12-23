using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using System.Data;
using System.Data.SqlClient;
//using System.Data.Entity;
//using System.Data.Entity.Infrastructure;
using BackendApp.Models;

namespace BackendApp.Controllers
{
    public class ImagesController : ApiController
    {
        // Get api/images/count
        public IEnumerable<ImageCount> GetImages()
        {
            ImageCount[] count = null;
            SqlConnection con = new SqlConnection(@"Data Source=MALU-PC\SQLEXPRESS;Initial Catalog=TestDB;Integrated Security=True;");
            con.Open();
            SqlCommand cmd1 = new SqlCommand("SELECT Count(IMAGE_PATH) AS number FROM Images", con);
            SqlDataReader dr1 = cmd1.ExecuteReader();
              while (dr1.Read())
            {
                count = new ImageCount[]
                {
                new ImageCount { Number = dr1.GetInt32(0) }
                         
                 };
            }
         
           
            dr1.Close();
            con.Close();

            return count;
        }
    
  
        //Get api/images/{index}
        public IEnumerable<Image> GetImageDetails(int id)
        {

            Image[] images = null;

            String index = id.ToString();
            SqlConnection con = new SqlConnection(@"Data Source=MALU-PC\SQLEXPRESS;Initial Catalog=TestDB;Integrated Security=True;");
            con.Open();
            SqlCommand cmd = new SqlCommand("SELECT IMAGE_ID,IMAGE_NAME,IMAGE_PATH FROM Images WHERE IMAGE_ID=" + index, con);



            SqlDataReader dr = cmd.ExecuteReader();

            while (dr.Read())
            {

                images = new Image[]
                {
                new Image { Id=dr.GetValue(0).ToString() , Name=dr.GetValue(1).ToString(), Path=dr.GetValue(2).ToString() }
                         
                 };

            }


            dr.Close();
            con.Close();
            return images;

        }
    
        /**     public IHttpActionResult GetProduct(int id)
             {

                 var product = products.FirstOrDefault((p) => p.Id == id);
                 if( product == null)
                 {
                     return NotFound();
                 }

                 return Ok(product);


             }
          **/

        

    }
}

