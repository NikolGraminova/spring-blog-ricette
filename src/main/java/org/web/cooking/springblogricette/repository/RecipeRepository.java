package org.web.cooking.springblogricette.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.cooking.springblogricette.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
