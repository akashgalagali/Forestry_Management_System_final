package com.cg.fms.service;
/******************************
 * @author       Anitha J Saini
 * Description : This is the Service Implementation class for Products. 
 * Created Date: 22 April, 2021 
 * Version     : v1.1.0
 *****************************/
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.IProductDao;
import com.cg.fms.dto.Product;
@Service
public class ProductServiceImpl implements ProductService

	{
		@Autowired
		IProductDao pdao;
		@Override
		public Optional<Product> serviceGetProduct(int productId) {
			return pdao.findById(productId);
		}
		
		@Override
		public boolean serviceAddProduct(Product product) {
			pdao.save(product);
			return false;
		}
		

		@Override
		public boolean serviceUpdateProduct(Product product) {
			pdao.save(product);
			return false;
		}

		@Override
		public boolean serviceDeleteProduct(int productId) {
			List<Product> pro =pdao.findAll();
			Product p= pdao.findById(productId).get();
			pro.remove(p);
			pdao.deleteById(productId);
			return true;
		}

		@Override
		public List<Product> serviceGetAllProducts() {
			
			return pdao.findAll() ;
		}

		

	
}
