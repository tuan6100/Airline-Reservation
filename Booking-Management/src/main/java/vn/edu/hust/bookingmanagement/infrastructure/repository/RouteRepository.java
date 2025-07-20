package vn.edu.hust.bookingmanagement.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.bookingmanagement.infrastructure.orm.RouteOrm;


public interface RouteRepository extends JpaRepository<RouteOrm, Integer> {



}