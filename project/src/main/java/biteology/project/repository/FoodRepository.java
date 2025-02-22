package biteology.project.repository;

import biteology.project.entity.Disease;
import biteology.project.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FoodRepository extends JpaRepository<Food, String> {
}
