import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Customer} from '../../dto/customer';
import {NgForm} from "@angular/forms";
import {CustomerService} from "../../services/customer.service";

@Component({
  selector: 'app-customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.scss']
})
export class CustomerFormComponent {
  @Input() display = false;
  @Output() displayChange: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() customer: Customer = new Customer();


  constructor(private customerService: CustomerService) {
  }

  onFormSubmit(f: NgForm) {
    if (!f.valid) {
      return;
    }
    this.customerService.createCustomer(this.customer).subscribe(res => {
      console.log(res);
      this.cancel();
    });
  }

  cancel() {
    this.displayChange.emit(false);
    this.customer = new Customer();
  }
}
