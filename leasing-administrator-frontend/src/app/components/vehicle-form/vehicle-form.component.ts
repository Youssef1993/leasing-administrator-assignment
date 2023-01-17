import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgForm} from "@angular/forms";
import {VehicleService} from "../../services/vehicle.service";
import {Vehicle} from "../../dto/vehicle";

@Component({
  selector: 'app-vehicle-form',
  templateUrl: './vehicle-form.component.html',
  styleUrls: ['./vehicle-form.component.scss']
})
export class VehicleFormComponent {
  @Input() display = false;
  @Output() displayChange: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() vehicle: Vehicle = new Vehicle();
  @Output() vehicleUpdated: EventEmitter<Vehicle> = new EventEmitter<Vehicle>();
  @Output() vehicleAdded: EventEmitter<Vehicle> = new EventEmitter<Vehicle>();


  constructor(private vehicleService: VehicleService) {
  }

  onFormSubmit(f: NgForm) {
    if (!f.valid) {
      return;
    }
    if (this.vehicle.id) {
      this.updateVehicle();
    } else {
      this.saveNewVehicle();
    }
  }

  saveNewVehicle() {
    this.vehicleService.createVehicle(this.vehicle).subscribe(res => {
      this.vehicleAdded.emit(res);
      this.closePopup();
    });
  }

  updateVehicle() {
    this.vehicleService.updateVehicle(this.vehicle).subscribe(() => {
      this.vehicleUpdated.emit(this.vehicle);
      this.closePopup();
    });
  }

  closePopup() {
    this.displayChange.emit(false);
    this.vehicle = new Vehicle();
  }

}
