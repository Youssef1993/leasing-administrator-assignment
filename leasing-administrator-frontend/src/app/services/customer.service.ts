import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Constants} from "../constants";
import {Customer} from "../dto/customer";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private readonly CUSTOMER_API = Constants.BACKEND_URL + '/customers'
  constructor(private http: HttpClient) { }

  createCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.CUSTOMER_API, customer);
  }
}
