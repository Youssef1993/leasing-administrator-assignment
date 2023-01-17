import {Component, OnInit} from '@angular/core';
import {Customer} from "../../dto/customer";
import {CustomerService} from "../../services/customer.service";

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.scss']
})
export class CustomerListComponent implements OnInit{
  displayForm = false;
  selectedCustomer: Customer = new Customer();
  customers: Array<Customer> = new Array<Customer>();


  constructor(private customerService: CustomerService) {
  }

  ngOnInit(): void {
    this.getListOfCustomers();
  }

  createNewCustomer() {
    this.displayForm = true;
    this.selectedCustomer = new Customer();
  }

  getListOfCustomers() {
    this.customerService.getCustomerList().subscribe(res => {
      this.customers = res;
    })
  }

  editCustomer(customer: Customer) {
    customer = JSON.parse(JSON.stringify(customer))
    customer.birthDate = new Date(customer.birthDate);
    this.selectedCustomer = customer;
    this.displayForm = true;
  }

  onCustomerUpdate(customer: Customer) {
    const oldCustomerIndex = this.customers.findIndex(c => c.id === customer.id);
    if (oldCustomerIndex < 0) {
      return;
    }
    this.customers.splice(oldCustomerIndex, 1, customer);
  }

}
