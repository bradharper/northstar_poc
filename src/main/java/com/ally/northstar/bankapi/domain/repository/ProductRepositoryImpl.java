package com.ally.northstar.bankapi.domain.repository;

import com.ally.northstar.bankapi.domain.model.Product;
import io.crnk.core.exception.ResourceNotFoundException;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.resource.list.ResourceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductRepositoryImpl implements ProductRepository {

	private static final Map<Long, Product> REPOSITORY = new ConcurrentHashMap<>();

	private static final AtomicLong ID_GENERATOR = new AtomicLong(4);

	private AccountRepositoryImpl accountRepository;

	@Autowired
	public ProductRepositoryImpl(AccountRepositoryImpl accountRepository) {
		this.accountRepository = accountRepository;
		Product product = new Product(1L, "Some Product");
		product.setAccountId(121L);
		save(product);
	}

	@Override
	public <S extends Product> S save(S entity) {
		if (entity.getId() == null) {
			entity.setId(ID_GENERATOR.getAndIncrement());
		}
		REPOSITORY.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public <S extends Product> S create(S entity) {
		return save(entity);
	}

	@Override
	public Class<Product> getResourceClass() {
		return Product.class;
	}

	@Override
	public Product findOne(Long productId, QuerySpec querySpec) {
		Product product = REPOSITORY.get(productId);
		if (product == null) {
			throw new ResourceNotFoundException("Product not found!");
		}
		if (product.getAccount() == null) {
			product.setAccount(accountRepository.findOne(product.getAccountId(), new QuerySpec(Product.class)));
		}
		return product;
	}

	@Override
	public ResourceList<Product> findAll(QuerySpec querySpec) {
		return querySpec.apply(REPOSITORY.values());
	}

	@Override
	public ResourceList<Product> findAll(Iterable<Long> productIds, QuerySpec querySpec) {
		List<Product> foundProducts = new ArrayList<>();
		for (Map.Entry<Long, Product> entry : REPOSITORY.entrySet()) {
			for (Long id : productIds) {
				if (id.equals(entry.getKey())) {
					foundProducts.add(entry.getValue());
				}
			}
		}
		return querySpec.apply(foundProducts);
	}

	@Override
	public void delete(Long productId) {
		REPOSITORY.remove(productId);
	}
}
