import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Customer} from '../../dto/customer';
import {NgForm} from "@angular/forms";
import {CustomerService} from "../../services/customer.service";
import {CustomerListComponent} from "../customer-list/customer-list.component";

@Component({
  selector: 'app-customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.scss']
})
export class CustomerFormComponent {
  @Input() display = false;
  @Output() displayChange: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() customer: Customer = new Customer();
  @Output() customerUpdated: EventEmitter<Customer> = new EventEmitter<Customer>();
  @Output() customerAdded: EventEmitter<Customer> = new EventEmitter<Customer>();


  constructor(private customerService: CustomerService) {
  }

  onFormSubmit(f: NgForm) {
    if (!f.valid) {
      return;
    }
    if (this.customer.id) {
      this.updateCustomer();
    } else {
      this.saveNewCustomer();
    }
  }

  saveNewCustomer() {
    this.customerService.createCustomer(this.customer).subscribe(res => {
      this.customerAdded.emit(res);
      this.closePopup();
    });
  }

  updateCustomer() {
    this.customerService.updateCustomer(this.customer).subscribe(() => {
      this.customerUpdated.emit(this.customer);
      this.closePopup();
    });
  }

  closePopup() {
    this.displayChange.emit(false);
    this.customer = new Customer();
  }
}
