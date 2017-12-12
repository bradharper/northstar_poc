/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ally.northstar.bankapi.domain.repository;

import com.ally.northstar.bankapi.domain.model.Account;
import com.ally.northstar.bankapi.domain.model.Product;
import com.ally.northstar.bankapi.domain.model.Project;
import com.ally.northstar.bankapi.domain.model.Task;
import io.crnk.core.engine.internal.utils.PropertyUtils;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.RelationshipRepositoryV2;
import io.crnk.core.resource.list.ResourceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Manually-written, annotation-based relationship repository example.
 */
@Component
public class AcountToProductRepositoryImpl implements RelationshipRepositoryV2<Account, Long, Product, Long> {

	private final AccountRepository accountRepository;

	private final ProductRepository productRepository;

	@Autowired
	public AcountToProductRepositoryImpl(AccountRepository accountRepository, ProductRepository productRepository) {
		this.accountRepository = accountRepository;
		this.productRepository = productRepository;
	}

	@Override
	public Class<Account> getSourceResourceClass() {
		return Account.class;
	}

	@Override
	public Class<Product> getTargetResourceClass() {
		return Product.class;
	}

	@Override
	public void setRelation(Account account, Long productId, String fieldName) {
		Product product = productRepository.findOne(productId, null);
		try {
			PropertyUtils.setProperty(account, fieldName, product);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		accountRepository.save(account);
	}

	@Override
	public void setRelations(Account account, Iterable<Long> productIds, String fieldName) {
		Iterable<Product> products = productRepository.findAll(productIds, null);
		try {
			PropertyUtils.setProperty(account, fieldName, products);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		accountRepository.save(account);
	}

	@Override
	public void addRelations(Account account, Iterable<Long> productIds, String fieldName) {
		List<Product> newProductList = new LinkedList<>();
		Iterable<Product> productsToAdd = productRepository.findAll(productIds, null);
		for (Product product : productsToAdd) {
			newProductList.add(product);
		}
		if (PropertyUtils.getProperty(account, fieldName) != null) {
			Iterable<Product> products = (Iterable<Product>) PropertyUtils.getProperty(account, fieldName);
			for (Product product : products) {
				newProductList.add(product);
			}
		}
		PropertyUtils.setProperty(account, fieldName, newProductList);
		accountRepository.save(account);

	}

	@Override
	public void removeRelations(Account account, Iterable<Long> productIds, String fieldName) {
		try {
			if (PropertyUtils.getProperty(account, fieldName) != null) {
				Iterable<Product> products = (Iterable<Product>) PropertyUtils.getProperty(account, fieldName);
				Iterator<Product> iterator = products.iterator();
				while (iterator.hasNext()) {
					for (Long productIdToRemove : productIds) {
						if (iterator.next().getId().equals(productIdToRemove)) {
							iterator.remove();
							break;
						}
					}
				}
				List<Product> newProductList = new LinkedList<>();
				for (Product product : products) {
					newProductList.add(product);
				}

				PropertyUtils.setProperty(account, fieldName, newProductList);
				accountRepository.save(account);
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Product findOneTarget(Long accountId, String fieldName, QuerySpec requestParams) {
		Account account = accountRepository.findOne(accountId, requestParams);
		return (Product) PropertyUtils.getProperty(account, fieldName);
	}

	@Override
	public ResourceList<Product> findManyTargets(Long accountId, String fieldName, QuerySpec requestParams) {
		Account account = accountRepository.findOne(accountId, requestParams);
		return requestParams.apply((Iterable<Product>) PropertyUtils.getProperty(account, fieldName));
	}
}
