package com.example.demo.model;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prodName;    
    private String prodDesc;    
    private String prodImage;
    private Double prodPrice;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    //@Temporal(TemporalType.DATE)
    
    private Date prodDate;
    private byte[] prodImage1;
    
    public Product()
    {
        
    }

    public Product(String prodName, String prodDesc, String prodImage, Double prodPrice,Date prodDate,byte prodImage1) {
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodImage = prodImage;
        this.prodPrice = prodPrice;
        this.prodDate = prodDate;
        this.prodImage1 = this.prodImage1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public String getProdImage() {
        return prodImage;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage;
    }

    public Double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Double prodPrice) {
        this.prodPrice = prodPrice;
    }  

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public byte[] getProdImage1() {
        return prodImage1;
    }

    public void setProdImage1(byte[] prodImage1) {
        this.prodImage1 = prodImage1;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", prodName=" + prodName + ", prodDesc=" + prodDesc + ", prodImage=" + prodImage + ", prodPrice=" + prodPrice + ", prodDate=" + prodDate + ", prodImage1=" + Arrays.toString(prodImage1) + '}';
    }
    
    
    
}
