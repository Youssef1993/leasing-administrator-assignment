import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgForm} from "@angular/forms";
import {LeasingContract} from "../../dto/leasing-contract";
import {LeasingContractService} from "../../services/leasing-contract.service";
import {Customer} from "../../dto/customer";
import {Vehicle} from "../../dto/vehicle";
import {VehicleService} from "../../services/vehicle.service";
import {CustomerService} from "../../services/customer.service";

@Component({
  selector: 'app-leasing-contract-form',
  templateUrl: './leasing-contract-form.component.html',
  styleUrls: ['./leasing-contract-form.component.scss']
})
export class LeasingContractFormComponent implements OnInit{
  @Input() display = false;
  @Output() displayChange: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() contract: LeasingContract = new LeasingContract();
  @Output() contractUpdated: EventEmitter<LeasingContract> = new EventEmitter<LeasingContract>();
  @Output() contractAdded: EventEmitter<LeasingContract> = new EventEmitter<LeasingContract>();
  @Input() mode: 'CREATE' | 'UPDATE';

  customers: Array<Customer> = new Array<Customer>();
  vehicles: Array<Vehicle> = new Array<Vehicle>();


  constructor(private contractService: LeasingContractService,
              private vehicleService: VehicleService,
              private customerService: CustomerService) {
  }

  ngOnInit(): void {
    this.getCustomers();
    this.getVehicles();
  }

  getCustomers() {
    this.customerService.getCustomerList().subscribe(res => {
      this.customers = res;
    });
  }

  getVehicles() {
    this.vehicleService.getVehiclesWithoutContract().subscribe(res => {
      this.vehicles = res;
    });
  }

  onFormSubmit(f: NgForm) {
    if (!f.valid) {
      return;
    }
    if (this.mode == 'UPDATE') {
      this.updateLeasingContract();
    } else {
      this.saveNewLeasingContract();
    }
  }

  saveNewLeasingContract() {
    this.contractService.createLeasingContract(this.contract).subscribe(res => {
      this.contractAdded.emit(res);
      this.closePopup();
    });
  }

  updateLeasingContract() {
    this.contractService.updateLeasingContract(this.contract).subscribe(() => {
      this.contractUpdated.emit(this.contract);
      this.closePopup();
    });
  }

  closePopup() {
    this.displayChange.emit(false);
    this.contract = new LeasingContract();
  }
}
