import { RooterModule } from './rooter.module';

describe('RooterModule', () => {
  let rooterModule: RooterModule;

  beforeEach(() => {
    rooterModule = new RooterModule();
  });

  it('should create an instance', () => {
    expect(rooterModule).toBeTruthy();
  });
});
