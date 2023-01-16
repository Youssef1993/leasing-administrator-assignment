import { Component } from '@angular/core';
import {Customer} from "../../dto/customer";

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.scss']
})
export class CustomerListComponent {
  displayForm = false;
  selectedCustomer: Customer = new Customer();

  createNewCustomer() {
    this.displayForm = true;
    this.selectedCustomer = new Customer();
  }
}
